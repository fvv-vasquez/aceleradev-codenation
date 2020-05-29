package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	List<Time> times = new ArrayList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		if (times.contains(time)) {
			throw new IdentificadorUtilizadoException("Time já existe");
		}
		times.add(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		Time time = buscarTime(idTime);
		time.addJogador(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));

	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = buscarJogador(idJogador);
		Time time = buscarTime(jogador.getIdTime());
		time.setCapitao(jogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Jogador jogador = buscarTime(idTime).getCapitao();
		if (jogador == null) {
			throw new CapitaoNaoInformadoException();
		}
		return jogador.getId();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return buscarJogador(idJogador).getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return buscarTime(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		return buscarTime(idTime).getJogadores()
				.stream()
				.map(Jogador::getId)
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		return compararJogador(idTime, Comparator.comparing(Jogador::getNivelHabilidade)).getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		return compararJogador(idTime, Comparator.comparing(Jogador::getDataNascimento, Comparator.reverseOrder())).getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times.stream()
				.mapToLong(Time::getId)
				.boxed()
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		return compararJogador(idTime, Comparator.comparing(Jogador::getSalario)).getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return buscarJogador(idJogador).getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return times.stream()
				.map(Time::getJogadores)
				.flatMap(jogadores -> jogadores.stream())
				.sorted((j1, j2) -> j2.getNivelHabilidade().compareTo(j1.getNivelHabilidade()))
				.limit(top)
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time timeCasa = buscarTime(timeDaCasa);
		Time timeFora = buscarTime(timeDeFora);
		if (timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())) {
			return timeFora.getCorUniformeSecundario();
		} else {
			return timeFora.getCorUniformePrincipal();
		}
	}

	private Jogador compararJogador(Long idTime, Comparator<Jogador> comparator) {
		return buscarTime(idTime).getJogadores().stream().sorted(comparator.reversed().thenComparing(Jogador::getId))
				.findFirst().get();
	}

	private Jogador buscarJogador(Long id) {
		return times.stream().map(Time::getJogadores)
				.flatMap(jogadores -> jogadores.stream().filter(j -> j.getId().equals(id))).findFirst()
				.orElseThrow(JogadorNaoEncontradoException::new);
	}

	public Time buscarTime(Long id) {
		return times.stream().filter(time -> time.getId().equals(id))
				.findFirst()
				.orElseThrow(TimeNaoEncontradoException::new);
	}
}
