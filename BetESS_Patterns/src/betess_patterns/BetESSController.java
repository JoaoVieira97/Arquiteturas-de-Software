package betess_patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class BetESSController {
    
    private BetESSModel model;
    private BetESSView view;
    
    public void setView(BetESSView v){
        this.view = v;
    }

    public void setModel(BetESSModel m){
        this.model = m;
    }
    
    public void startFlow() {
        Menu menu = view.getMenu(1);
        String opcao;
        do {
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "L" :
                    login();
                    break;
                case "R" :
                    registar();
                    break;
                case "S":
                    break;
                default:
                    System.out.println("Opcão Inválida!");
                    break;
            }
        } while(!opcao.equals("S"));
    }
    
    private void login(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira os seus dados:");
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Password: ");
        String password = scan.nextLine();
        int login = this.model.login(email, password);
        String nome;
        switch (login) {
            case 1:
                nome = model.getUtilizador(email).getNome();
                System.out.println("Login como apostador efetuado com sucesso. Bem-vindo " + nome + "!");
                flowApostador(email);
                break;
            case 2:
                nome = model.getUtilizador(email).getNome();
                System.out.println("Login como funcionário efetuado com sucesso. Bem-vindo " + nome + "!");
                flowFuncionario(email);
                break;
            case 0:
                System.out.println("Password inserida está incorreta");
                break;
            case -1:
                System.out.println("Não existe o utlizador com o email inserido");
                break;
            default:
                break;
        }
    }
    
    private void registar() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o seu email:");
        String email = scan.nextLine();
        if (this.model.existeUtilizador(email)){
            System.out.println("Já existe um utilizador com o email inserido");
            return;
        }
        System.out.println("Insira o seu nome:");
        String nome = scan.nextLine();
        System.out.println("Insira a sua password:");
        String password = scan.nextLine();
        System.out.println("Qual o valor que pretende carregar na sua conta?");
        Scanner scanD = new Scanner(System.in);
        double saldo = scanD.nextDouble();
        model.addApostador(email, password, nome, saldo);
        System.out.println("Registo efetuado com sucesso");
    }
    
    //------------------------------APOSTADOR------------------------------
    
    private void flowApostador(String email) {
        Menu menu = view.getMenu(2);
        String opcao;
        do {
            List<String> noti = ((Apostador) model.getUtilizador(email)).getNotificacoes();
            if (noti.isEmpty()) menu.show();
            else this.view.menuApostadorNotificacoes(noti.size()).show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "N" :
                    if (noti.isEmpty()){ System.out.println("Opcão Inválida!"); break;}
                    this.view.printNotificacoes(noti);
                    ((Apostador) model.getUtilizador(email)).cleanNotificacoes();
                    break;
                case "E" :
                    mostrarEventos();
                    break;
                case "V" :
                    verApostas(email);
                    break;
                case "A" :
                    novaAposta(email);
                    break;
                case "C" :
                    imprimeSaldo(email);
                    break;
                case "I" :
                    carregarConta(email);
                    break;
                case "S": 
                    break;
                default: System.out.println("Opcão Inválida!"); break;
            }
        } while(!opcao.equals("S"));
        System.out.println("Até à próxima visita " + this.model.getUtilizador(email).getNome() + "!");
    }
    
    public void mostrarEventos(){
        List<Evento> eventos = this.model.getListaEventos();
        Menu menu = this.view.getMenu(5);
        String opcao;
        do{
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "T":
                    this.view.printCollectionEventos("Todos os eventos:", eventos);
                    break;
                case "C":
                    System.out.println("Qual a competição que pretende procurar?");
                    String competicao = scan.next();
                    this.model.iterateEventos(1);
                    this.view.printIteratorEventos("Eventos pela procura de competição '" + competicao + "':", this.model.getIterator_result(eventos, competicao));
                    break;
                case "E":
                    System.out.println("Qual a equipa que pretende procurar?");
                    String equipa = scan.next();
                    this.model.iterateEventos(2);
                    this.view.printIteratorEventos("Eventos pela procura de equipa '" + equipa + "':", this.model.getIterator_result(eventos, equipa));
                    break;
                case "M":
                    this.model.sortEventos(eventos);
                    this.view.printCollectionEventos("Eventos ordenados pelo nº de apostas:", eventos);
                    break;
                case "R":
                    break;
                default: System.out.println("Opcão Inválida!"); break;
                
            }
        } while(!opcao.equals("R"));
    }
    
    public void verApostas(String email){
        Menu menu = this.view.getMenu(7);
        String opcao;
        do{
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "S":
                    ((Apostador) this.model.getUtilizador(email)).showApostas();
                    break;
                case "G":
                    ((Apostador) this.model.getUtilizador(email)).showApostas(2);
                    break;
                case "P":
                    ((Apostador) this.model.getUtilizador(email)).showApostas(1);
                    break;
                case "A":
                    ((Apostador) this.model.getUtilizador(email)).showApostas(3);
                    break;
                case "V":
                    break;
                default: System.out.println("Opcão Inválida!"); break;   
            }
        } while(!opcao.equals("V"));
    }
    
    private void novaAposta(String email){
        Menu menu = this.view.getMenu(6);
        String opcao;
        do{
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "S":
                    novaApostaSimples(email);
                    break;
                case "M":
                    novaApostaMultipla(email);
                    break;
                case "C":
                    break;
                default: System.out.println("Opcão Inválida!"); break;
                
            }
        } while(!opcao.equals("C"));
    }
    
    private void novaApostaSimples(String email){
        String opcao;
        System.out.print("Insira o id do evento em que pretende apostar: ");
        Scanner scanI = new Scanner(System.in);
        int id = scanI.nextInt();
        if (!this.model.existeEvento(id)){
            System.out.println("O evento com o id inserido não existe");
            return;
        }
        else if (!this.model.getEvento(id).getDisponibilidade()){
            System.out.println("O evento escolhido não está disponível para apostas de momento");
            return;
        }
        Apostador apostador = (Apostador) this.model.getUtilizador(email);
        System.out.println("Indique a quantia que pretende apostar: (Pode apostar até " + apostador.getSaldo() + " ESScoins)");
        Scanner scanD = new Scanner(System.in);
        double quantia = scanD.nextDouble();
        if (!apostador.saldoSufiente(quantia)){
            System.out.println("O seu saldo é insuficiente para realizar a aposta desejada!");
            return;
        }
        Evento evento = this.model.getEvento(id);
        do{
            this.view.menuEquipas(evento, quantia);
            System.out.println("Insira a sua escolha:");
            Scanner scan = new Scanner(System.in);
            opcao = scan.next().toUpperCase();
            switch(opcao){
                case "1":
                    apostador.apostaSimples(0, quantia, evento.getOdds()[0], evento);
                    apostador.removeQuantia(quantia);
                    System.out.println("Aposta realizada na equipa " + evento.getEquipa_1() + "!");
                    evento.registerObserver(apostador);
                    evento.incNumApostas();
                    evento.changeBalanco(quantia);
                    return;
                case "X":
                    apostador.apostaSimples(1, quantia, evento.getOdds()[1], evento);
                    apostador.removeQuantia(quantia);
                    System.out.println("Aposta realizada no empate!");
                    evento.registerObserver(apostador);
                    evento.incNumApostas();
                    evento.changeBalanco(quantia);
                    return;
                case "2":
                    apostador.apostaSimples(2, quantia, evento.getOdds()[2], evento);
                    apostador.removeQuantia(quantia);
                    System.out.println("Aposta realizada na equipa " + evento.getEquipa_2() + "!");
                    evento.registerObserver(apostador);
                    evento.incNumApostas();
                    evento.changeBalanco(quantia);
                    return;
                case "C":
                    break;
                default:
                    System.out.println("Opcão Inválida! Tente de novo.");
                    break;
            }
        } while(!opcao.equals("C")); 
    }
    
    public void novaApostaMultipla(String email){
        String opcao;
        System.out.print("Insira os id's do eventos em que pretende apostar separados por espaços: ");
        Scanner scan = new Scanner(System.in);
        String eventos_string = scan.nextLine();
        int[] eventos_id = Stream.of(eventos_string.split("\\s+")).mapToInt(Integer::parseInt).toArray();
        List<Integer> aux = new ArrayList<>();
        for (Integer e : eventos_id){
            if (aux.contains(e)){
                System.out.println("Não pode efetuar uma aposta múltipla com eventos repetidos!");
                return;
            }
            aux.add(e);
        }
        for (Integer e : eventos_id){
            if (!this.model.existeEvento(e)){
                System.out.println("O evento com o id " + e + " não existe");
                return;
            }
        }
        for (Integer e: eventos_id){
            if (!this.model.getEvento(e).getDisponibilidade()){
                System.out.println("O evento com o id " + e + " não está disponível para apostas de momento");
                return;
            }
        }
        Apostador apostador = (Apostador) this.model.getUtilizador(email);
        System.out.println("Indique a quantia que pretende apostar: (Pode apostar até " + apostador.getSaldo() + " ESScoins)");
        Scanner scanD = new Scanner(System.in);
        double quantia = scanD.nextDouble();
        if (!apostador.saldoSufiente(quantia)){
            System.out.println("O seu saldo é insuficiente para realizar a aposta desejada!");
            return;
        }
        int[] resultados = new int[eventos_id.length];
        Evento[] eventos = new Evento[eventos_id.length];
        int i = 0;
        boolean ok;
        for (Integer e : eventos_id){
            ok = false;
            eventos[i] = this.model.getEvento(e);
            while (!ok){
                this.view.menuEquipasMultipla(eventos[i]);
                System.out.println("Insira a sua escolha: ");
                //scan.next();
                opcao = scan.next().toUpperCase();
                switch (opcao){
                    case "1":
                        resultados[i] = 0;
                        System.out.println("Aposta realizada na equipa " + eventos[i].getEquipa_1() + "!");
                        eventos[i].registerObserver(apostador);
                        eventos[i].incNumApostas();
                        ok = true;
                        break;
                    case "X":
                        resultados[i] = 1;
                        System.out.println("Aposta realizada no empate!");
                        eventos[i].registerObserver(apostador);
                        eventos[i].incNumApostas();
                        ok = true;
                        break;
                    case "2":
                        resultados[i] = 2;
                        System.out.println("Aposta realizada na equipa " + eventos[i].getEquipa_2() + "!");
                        eventos[i].registerObserver(apostador);
                        eventos[i].incNumApostas();
                        ok = true;
                        break;
                    default:
                        break;
                }
            }
            i++;
        }
        apostador.apostaMultipla(quantia, resultados, eventos);
        apostador.removeQuantia(quantia);
        
        System.out.println("Aposta múltipla efetuada com sucesso");
    }
    
    private void imprimeSaldo(String email){
        System.out.println("O saldo atual da conta é de " + ((Apostador) this.model.getUtilizador(email)).getSaldo() + " ESScoins");
    }
    
    private void carregarConta(String email){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira a quantidade monetária que pretende carregar na sua conta");
        System.out.print("Quantia : ");
        double q = scan.nextDouble();
        double novo_saldo = ((Apostador) this.model.getUtilizador(email)).getSaldo() + q;
        ((Apostador) this.model.getUtilizador(email)).setSaldo(novo_saldo);
        System.out.println("Carregamento efetuado com sucesso! O seu saldo é de agora " + novo_saldo + " ESScoins");
    }
    
    //-----------------------------FUNCIONÁRIO-----------------------------
        
    private void flowFuncionario(String email) {
        Menu menu = view.getMenu(3);
        String opcao;
        do {
            List<String> noti = ((Funcionario) model.getUtilizador(email)).getNotificacoes();
            if (noti.isEmpty()) menu.show();
            else this.view.menuFuncionarioNotificacoes(noti.size()).show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "N" :
                    if (noti.isEmpty()){ System.out.println("Opcão Inválida!"); break;}
                    this.view.printNotificacoes(noti);
                    ((Funcionario) model.getUtilizador(email)).cleanNotificacoes();
                    break;
                case "E" :
                    mostrarEventos();
                    break;
                case "A" :
                    adicionarEvento();
                    break;
                case "M" :
                    flowModificar();
                    break;
                case "O" :
                    observarEvento(email);
                    break;
                case "B" :
                    mostrarObservarAposta(email);
                    break;
                case "T" :
                    terminarEvento();
                    break;
                case "S": 
                    break;
                default: System.out.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));
        System.out.println("Até à próxima visita " + model.getUtilizador(email).getNome() + "!");
    }
    
    private void adicionarEvento(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o nome das 2 equipas envolvidas no jogo:");
        System.out.print("Equipa nº1 : ");
        String equipa_1 = scan.nextLine();
        System.out.print("Equipa nº2 : ");
        String equipa_2 = scan.nextLine();
        System.out.print("Nome da Competição : ");
        String competicao = scan.nextLine();
        Scanner scanD = new Scanner(System.in);
        System.out.println("Insira as odd's para os 3 possíveis resultados:");
        System.out.print("Vitória do/da " + equipa_1 + ": ");
        double odd_1 = scanD.nextDouble();
        System.out.print("Empate: ");
        double odd_x = scanD.nextDouble();
        System.out.print("Vitória do/da " + equipa_2 + ": ");
        double odd_2 = scanD.nextDouble();
        System.out.print("O evento pode estar disponível de momento (S/N): ");
        String d = scan.nextLine().toUpperCase();
        boolean disponibilidade;
        switch(d){
            case "S": disponibilidade = true; break;
            case "N": disponibilidade = false; break;
            default: System.out.println("Opção inválida"); return;
        }
        this.model.addEvento(equipa_1, equipa_2,competicao ,odd_1, odd_x, odd_2, disponibilidade, 0);
        System.out.println("Evento adicionado com sucesso");
    }
    
    private void flowModificar(){
        Scanner scanI = new Scanner(System.in);
        System.out.print("Insira o id do evento que pretende modificar: ");
        int id = scanI.nextInt();
        if (!this.model.existeEvento(id)){
            System.out.println("Não exister o evento com o id inserido");
            return;
        }
        Evento evento = this.model.getEvento(id);
        Menu menu = view.getMenu(4);
        String opcao;
        do {
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "D" :
                    modificarDisponibilidadeEvento(evento);
                    break;
                case "O" :
                    modificarOddsEvento(evento);
                    break;
                case "E" :
                    modificarEquipasEvento(evento);
                    break;
                case "C" :
                    modificarCompeticaoEvento(evento);
                    break;
                case "S": 
                    break;
                default: System.out.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));    
    }
    
    private void modificarDisponibilidadeEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira 'D' para colocar o evento como disponível ou 'I' para colocar o evento como indisponível");
        System.out.print("Opção : ");
        String opcao = scan.nextLine().toUpperCase();
        switch (opcao){
            case "D":
                evento.setDisponibilidade(true);
                System.out.println("Evento colocado como disponível");
                break;
            case "I":
                evento.setDisponibilidade(false);
                System.out.println("Evento colocado como indisponível");
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }
    
    private void modificarOddsEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira as odd's para os 3 resultados possíveis do evento:");
        System.out.print("Vitória do/da " + evento.getEquipa_1() + " : ");
        double odd_1 = scan.nextDouble();
        System.out.print("Empate : ");
        double odd_x = scan.nextDouble();
        System.out.print("Vitória do/da " + evento.getEquipa_2() + " : ");
        double odd_2 = scan.nextDouble();
        evento.setOdds(odd_1, odd_x, odd_2);
        System.out.println("Odd's do evento modificadas com sucesso");
    }
    
    private void modificarEquipasEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        System.out.println("De momento as duas equipas para aposta no evento são\nEquipa nº1 : " + evento.getEquipa_1() + "\nEquipa nº2 : " + evento.getEquipa_2());
        System.out.println("Insira o nome das equipas para editar o evento:");
        System.out.print("Equipa nº1 : ");
        String equipa_1 = scan.nextLine();
        System.out.print("Equipa nº2 : ");
        String equipa_2 = scan.nextLine();
        evento.setEquipa_1(equipa_1);
        evento.setEquipa_2(equipa_2);
        System.out.println("Equipas do evento modificadas com sucesso");
    }
    
    
    private void modificarCompeticaoEvento(Evento evento) {
        Scanner scan = new Scanner(System.in);
        System.out.println("De momento a competição deste evento é " + evento.getCompeticao());
        System.out.println("Insira o novo nome de competição para editar o evento:");
        System.out.print("Competição: ");
        String competicao = scan.nextLine();
        evento.setCompeticao(competicao);
        System.out.println("Competição do evento modificada com sucesso");
    }
    
    private void observarEvento(String email){
        System.out.println("Insira o id do evento que pretende observar:");
        Scanner scanI = new Scanner(System.in);
        int id = scanI.nextInt();
        if (!this.model.existeEvento(id)){
            System.out.println("Não existe o evento com o id inserido");
            return;
        }
        Evento evento = this.model.getEvento(id);
        Funcionario func = (Funcionario) this.model.getUtilizador(email);
        evento.registerObserver(func);
        System.out.println("Será notificado sobre o balanço das apostas simples deste evento assim que terminado!");
    }
    
    private void terminarEvento(){
        System.out.println("Insira o id do evento que pretende encerrar:");
        Scanner scanI = new Scanner(System.in);
        int id = scanI.nextInt();
        boolean e = this.model.existeEvento(id);
        if (!e){
            System.out.println("Não existe o evento com o id inserido");
            return;
        }
        boolean d = this.model.getDisponibilidadeEvento(id);
        if (!d){
            System.out.println("O evento com o id inserido não se encontra disponível de momento");
            return;
        }
        Evento evento = this.model.getEvento(id);
        evento.setDisponibilidade(false);
        System.out.println("Qual o resultado com que o evento terminou?");
        System.out.println("1 - Vitória do/da " + evento.getEquipa_1());
        System.out.println("X - Empate");
        System.out.println("2 - Vitória do/da " + evento.getEquipa_2());
        System.out.print("Opção : ");
        Scanner scan = new Scanner(System.in);
        String opcao = scan.nextLine().toUpperCase();
        int resultado = -1;
        switch (opcao){
            case "1": resultado = 0; break;
            case "X": resultado = 1; break;
            case "2": resultado = 2; break;
            default: break;
        }
        evento.notifyObservers(id, resultado);
        System.out.println("Evento encerrado com sucesso");
    }

    private void mostrarObservarAposta(String emailFunc) {
        
        Menu menu = this.view.getMenu(8);
        String opcao;
        do{
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "M":
                    this.model.showApostas();
                    break;
                case "O":
                    observarAposta(emailFunc);
                    break;
                case "V":
                    break;
                default: System.out.println("Opcão Inválida!"); break;
                
            }
        } while(!opcao.equals("V"));
        
    }
    private void observarAposta(String emailFunc) {
                
        
        System.out.println("Insira o email do apostador que pretende observar:");
        Scanner scanE = new Scanner(System.in);
        String email = scanE.nextLine();
        if (!this.model.existeUtilizador(email)){
            System.out.println("Não existe o apostador com o email inserido");
            return;
        }     
        System.out.println("Insira o id da aposta do apostador "+ email +" que pretende observar:");
        Scanner scanI = new Scanner(System.in);
        int id = scanI.nextInt();
        if (!this.model.existeAposta(email,id)){
            System.out.println("Não existe a aposta com o id inserido");
            return;
        }
        
        ApostaComponent aposta = this.model.getAposta(email,id) ;
        Observer func = (Funcionario) this.model.getUtilizador(emailFunc);
        aposta.registerObserver(func);
        System.out.println("Será notificado quando esta aposta sofrer alterações!");
    }

}
