package com.bhaskara.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

// TDD
// [1] Criar um teste que falhe (RED)
//     Então precisamos fazer com que o teste passe para sair desta etapa.
//     O código precisa ser o mais simples possivel. Podemos cometer pecados.
// [2] Fazer que o código que vai passar (GREEN)
// [3] Vamos refatorar o código para refletir a implementação real (REFACT)
class BhaskaraTests {
	@Test
	void given_ParametrosTexto_whenParametrosConvertidosReal_thenCalculeRaizes() {
		String inputA = "1", inputB = "-2", inputC = "-3";

		double a = Double.parseDouble(inputA);
		double b = Double.parseDouble(inputB);
		double c = Double.parseDouble(inputC);

		// colocar uma asserção devido a palavra 'deve'
		assertThat(Bhaskara.calcularRaizes(a, b, c)).contains(-1.0, 3.0);
	}

	@Test
	void given_ParametrosTexto_when_ParametroAIgualZero_then_LancarExcecao() {
		String inputA = "0", inputB = "-2", inputC = "-3";

		double a = Double.parseDouble(inputA);
		double b = Double.parseDouble(inputB);
		double c = Double.parseDouble(inputC);

		assertThatThrownBy(() -> Bhaskara.calcularRaizes(a, b, c))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Nao existem raizes reais");
	}
}
