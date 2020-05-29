package br.com.codenation.calculadora;

public class CalculadoraSalario {

	public long calcularSalarioLiquido (double salarioBase) {
		if (salarioBase > 1039.00) {
			double salarioLiquido = calcularIrrf(calcularInss(salarioBase));
			return Math.round(salarioLiquido);
		} else {
			return 0;
		}
	}
	
	private double calcularInss (final double salarioBase) {
		if (salarioBase <= 1500.00) {
			return salarioBase - (salarioBase * 0.08);
		} else if (salarioBase <= 4000.00) {
			return salarioBase - (salarioBase * 0.09);
		} else {
			return salarioBase - (salarioBase * 0.11);
		}
	}

	private double calcularIrrf (final double salarioComDesconto) {
		if (salarioComDesconto > 3000.00 && salarioComDesconto <= 6000.00) {
			return salarioComDesconto - (salarioComDesconto * 0.075);
		} else if (salarioComDesconto > 6000) {
			return salarioComDesconto - (salarioComDesconto * 0.15);
		} else {
			return salarioComDesconto;
		}
	}
}
