package com.bhaskara.service;

import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class BhaskaraTests {
	@Test
	void given_ParametrosTexto_whenParametrosConvertidosReal_thenCalculeRaizes() {
		String inputA = "1", inputB = "-2", inputC = "-3";

		Bhaskara.setCoeficientes(inputA, inputB, inputC);

		assertThat(Bhaskara.calcularRaizes()).contains(-1.0, 3.0);
	}

	@Test
	void given_ParametrosTexto_when_ParametroAIgualZero_then_LancarExcecao() {
		String inputA = "0", inputB = "-2", inputC = "-3";

		Bhaskara.setCoeficientes(inputA, inputB, inputC);

		assertThatThrownBy(Bhaskara::calcularRaizes)
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Nao existem raizes reais");
	}

	@Test
	void given_ParametrosTextoInvalidos_thenThrowsNumberFormatException(){

		String valueA = "G";
		String valueB = "2";
		String valueC = "-3";

		assertThatThrownBy(() -> Bhaskara.setCoeficientes(valueA, valueB, valueC))
				.isInstanceOf(NumberFormatException.class)
				.hasMessage("Por favor insira numeros reais!");

	}

	@Test
	void given_Parametros_Then_create_PDF() throws IOException {

		Map<String, String> valores = new HashMap<>();

		valores.put("Valor A", "1");
		valores.put("Valor B", "-2");
		valores.put("Valor C", "-3");

		BhaskaraReport.createPDF(valores);
		PDFUtil pdfUtil = new PDFUtil();
		pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);

		assertThat(pdfUtil.compare("bhaskaraDescription.pdf" , "compareDescription.pdf")).isTrue();
	}
}
