/**
 *
 * @author João Vieira & Simão Barbosa
 */
package betess_refactoring;

import java.util.Scanner;

public class Controller_Funcionario implements UserControllerInterface{
    
    private BetESSModel model;
    private BetESSView view;
    
    public void setView(BetESSView v){
        this.view = v;
    }
    
    public void setModel(BetESSModel m){
        this.model = m;
    }
    
    public void startFlow(String email) {
        Menu menu = view.getMenu(3);
        String opcao;
        do {
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "E" :
                    mostrarEventos(); break;
                case "A" :
                    adicionarEvento(); break;
                case "M" :
                    flowModificar(); break;
                case "T" :
                    terminarEvento(); break;
                case "S": break;
                default: view.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));
        view.println("Até à próxima visita " + model.getNomeUtilizador(email) + "!");
    }
    
    private void mostrarEventos() {
        view.printEventos(this.model.getListaEventos());
    }
    
    private void adicionarEvento(){
        String[] equipas = lerEquipas();
        double[] odds = lerOdds(equipas[0], equipas[1]);
        view.print("O evento pode estar disponível de momento (S/N): ");
        int d = lerDisponibilidade();
        if (d == -1) return;
        boolean disponibilidade = (d == 1) ? true : false;
        this.model.addEvento(equipas, odds, disponibilidade);
        view.println("Evento adicionado com sucesso");
    }
    
    private String[] lerEquipas(){
        Scanner scan = new Scanner(System.in);
        view.println("Insira o nome das 2 equipas envolvidas no jogo:");
        view.print("Equipa nº1 : ");
        String equipa_1 = scan.nextLine();
        view.print("Equipa nº2 : ");
        String equipa_2 = scan.nextLine();
        return new String[]{equipa_1, equipa_2};
    }
    
    private double[] lerOdds(String equipa_1, String equipa_2){
        Scanner scanD = new Scanner(System.in);
        view.println("Insira as odd's para os 3 possíveis resultados:");
        view.print("Vitória do/da " + equipa_1 + ": ");
        double odd_1 = scanD.nextDouble();
        view.print("Empate: ");
        double odd_x = scanD.nextDouble();
        view.print("Vitória do/da " + equipa_2 + ": ");
        double odd_2 = scanD.nextDouble();
        return new double[]{odd_1, odd_x, odd_2};
    }
    
    private int lerDisponibilidade(){
        Scanner scan = new Scanner(System.in);
        view.print("O evento pode estar disponível de momento (S/N): ");
        String d = scan.nextLine().toUpperCase();
        int disp;
        switch(d){
            case "S": disp = 1; break;
            case "N": disp = 2; break;
            default: view.println("Opção inválida");
                     disp = -1;
        }
        return disp;
    }
    
    private void flowModificar(){
        int id = lerEvento();
        if (id == -1) return;
        Evento evento = this.model.getEvento(id);
        Menu menu = view.getMenu(4);
        String opcao;
        do {
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next().toUpperCase();
            switch(opcao) {
                case "D" :
                    modificarDisponibilidadeEvento(evento); break;
                case "O" :
                    modificarOddsEvento(evento); break;
                case "E" :
                    modificarEquipasEvento(evento); break;
                case "S": break;
                default: view.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));    
    }
    
    private int lerEvento(){
        Scanner scanI = new Scanner(System.in);
        view.print("Insira o id do evento que pretende modificar: ");
        int id = scanI.nextInt();
        if (!this.model.existeEvento(id)){
            view.println("Não exister o evento com o id inserido");
            return -1;
        }
        return id;
    }
    
    private void modificarDisponibilidadeEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        view.println("Insira 'D' para colocar o evento como disponível ou 'I' para colocar o evento como indisponível");
        view.print("Opção : ");
        String opcao = scan.nextLine().toUpperCase();
        switch (opcao){
            case "D":
                evento.setDisponibilidade(true);
                view.println("Evento colocado como disponível"); break;
            case "I":
                evento.setDisponibilidade(false);
                view.println("Evento colocado como indisponível"); break;
            default:
                view.println("Opção inválida"); break;
        }
    }
    
    private void modificarOddsEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        view.println("Insira as odd's para os 3 resultados possíveis do evento:");
        view.print("Vitória do/da " + evento.getEquipa_1() + " : ");
        double odd_1 = scan.nextDouble();
        view.print("Empate : ");
        double odd_x = scan.nextDouble();
        view.print("Vitória do/da " + evento.getEquipa_2() + " : ");
        double odd_2 = scan.nextDouble();
        evento.setOdds(odd_1, odd_x, odd_2);
        view.println("Odd's do evento modificadas com sucesso");
    }
    
    private void modificarEquipasEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        view.println("De momento as duas equipas para aposta no evento são\nEquipa nº1 : " + evento.getEquipa_1() + "\nEquipa nº2 : " + evento.getEquipa_2());
        view.println("Insira o nome das equipas para editar o evento:");
        view.print("Equipa nº1 : ");
        String equipa_1 = scan.nextLine();
        view.print("Equipa nº2 : ");
        String equipa_2 = scan.nextLine();
        evento.setEquipa_1(equipa_1);
        evento.setEquipa_2(equipa_2);
        view.println("Equipas do evento modificadas com sucesso");
    }
    
    private void terminarEvento(){
        view.println("Insira o id do evento que pretende encerrar:");
        Scanner scanI = new Scanner(System.in);
        int id = scanI.nextInt();
        int m = this.model.mudarDisponibilidadeEvento(id, false);
        if (m == 0) {view.println("Não existe o evento com o id inserido"); return;}
        else if (m == 1){
            Evento evento = this.model.getEvento(id);
            this.view.opcoesTerminarEvento(evento);
            int resultado = lerResultado();
            notificarApostadores(evento, resultado);
            view.println("Evento encerrado com sucesso");
        }
    }
    
    private int lerResultado(){
        Scanner scan = new Scanner(System.in);
        String opcao = scan.nextLine().toUpperCase();
        int resultado = -1;
        switch (opcao){
            case "1": resultado = 0; break;
            case "X": resultado = 1; break;
            case "2": resultado = 2; break;
            default: view.println("Resultado inválido!"); break;
        }
        return resultado;
    }
    
    private void notificarApostadores(Evento evento, int resultado){
        Apostador apostador;
        for (String a : evento.getApostadores()){
            apostador = (Apostador) this.model.getUtilizador(a);
            apostador.eventoTerminado(evento.getId(),resultado);
        }
    }
    
}
