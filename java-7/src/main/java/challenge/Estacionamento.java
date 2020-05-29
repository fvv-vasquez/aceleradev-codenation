package challenge;

import java.util.Iterator;
import java.util.LinkedList;

public class Estacionamento {

    private final static int LIMITE_VAGAS = 10;
    private final static int IDADE_PREFERENCIAL = 55;

    private LinkedList<Carro> carros = new LinkedList<>();

    public void estacionar(Carro carro) {
        this.validarMotorista(carro.getMotorista());

        if (this.carrosEstacionados() == LIMITE_VAGAS) {
            for (Iterator<Carro> i = carros.iterator(); i.hasNext();) {
                if (i.next().getMotorista().getIdade() <= IDADE_PREFERENCIAL) {
                    i.remove();
                    break;
                }
            }
        }

        if (this.carrosEstacionados() == LIMITE_VAGAS) {
            throw new EstacionamentoException("Estacionamento lotado");
        }
        carros.add(carro);
    }

    public int carrosEstacionados() {
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carros.contains(carro);
    }

    private void validarMotorista(Motorista motorista) {
        if (motorista == null) {
            throw new EstacionamentoException("Carro autônomo não permitido");
        }
        if (motorista.getIdade() < 18) {
            throw new EstacionamentoException("Motorista de menor de idade não permitido.");
        }
        if (motorista.getPontos() > 20) {
            throw new EstacionamentoException("Pontos excedidos, habilitação suspensa.");
        }
    }
}
