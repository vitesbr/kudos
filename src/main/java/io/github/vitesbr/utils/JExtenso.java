package io.github.vitesbr.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Titulo: JExtenso
 * <p>
 * Descricao: Programa converte um numero para o valor em extenso
 * <p>
 *
 * @author Sergio Eduardo Rodrigues
 * @version 1.0
 * @created 10 de Janeiro de 2001
 */

public class JExtenso {

	private ArrayList<Integer> nro;

	private BigInteger num;

	private String Qualificadores[][] = {
			{ "centavo", "centavos" },
			{ "", "" },
			{ "mil", "mil" },
			{ "milhao", "milhoes" },
			{ "bilhao", "bilhoes" },
			{ "trilhao", "trilhoes" },
			{ "quatrilhao", "quatrilhoes" },
			{ "quintilhao", "quintilhoes" },
			{ "sextilhao", "sextilhoes" },
			{ "septilhao", "septilhoes" }
			};

	private String Numeros[][] = {
			{ "zero", "um", "dois", "tres", "quatro", "cinco", "seis", "sete",
					"oito", "nove", "dez", "onze", "doze", "treze", "quatorze",
					"quinze", "desesseis", "desessete", "dezoito", "desenove" },
			{ "vinte", "trinta", "quarenta", "cinquenta", "sessenta",
					"setenta", "oitenta", "noventa" },
			{ "cem", "cento", "duzentos", "trezentos", "quatrocentos",
					"quinhentos", "seiscentos", "setecentos", "oitocentos",
					"novecentos" }
			};

	public JExtenso() {
		this.nro = new ArrayList<Integer>();
	}

	public JExtenso(BigDecimal dec) {
		this();
		setNumber(dec);
	}

	public JExtenso(double dec) {
		this();
		setNumber(dec);
	}

	public void setNumber(BigDecimal dec) {

		// Converte para inteiro arredondando os centavos
		this.num = dec.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).toBigInteger();

		// Adiciona valores
		this.nro.clear();
		this.nro.trimToSize();

		if (this.num.equals(BigInteger.ZERO)) {

			// Centavos
			this.nro.add(new Integer(0));
			// Valor
			this.nro.add(new Integer(0));

		} else {

			// Adiciona centavos
			addRemainder(100);

			// Adiciona grupos de 1000
			while (!this.num.equals(BigInteger.ZERO)) {
				addRemainder(1000);
			}
		}
	}

	public void setNumber(double dec) {
		setNumber(new BigDecimal(dec));
	}

	public void show() {
		Iterator<Integer> valores = this.nro.iterator();

		while (valores.hasNext()) {
			System.out.println(valores.next().intValue());
		}

		System.out.println(toString());
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();

		int ct;

		for (ct = this.nro.size() - 1; ct > 0; ct--) {
			// Se ja existe texto e o atual nao e zero
			if (buf.length() > 0 && !ehGrupoZero(ct)) {
				buf.append(" e ");
			}
			buf.append(numToString(this.nro.get(ct).intValue(), ct));
		}

		if (buf.length() > 0) {

			if (ehUnicoGrupo()) {
				buf.append(" de ");
			}

			while (buf.toString().endsWith(" ")) {
				buf.setLength(buf.length() - 1);
			}

			if (ehPrimeiroGrupoUm()) {
				buf.insert(0, "h");
			}

			if (this.nro.size() == 2 && this.nro.get(1).intValue() == 1) {
				buf.append(" real");
			} else {
				buf.append(" reais");
			}

			if (this.nro.get(0).intValue() != 0) {
				buf.append(" e ");
			}
		}

		if (this.nro.get(0).intValue() != 0) {
			buf.append(numToString(this.nro.get(0).intValue(), 0));
		}

		return buf.toString();
	}

	private boolean ehPrimeiroGrupoUm() {
		return (this.nro.get(this.nro.size() - 1).intValue() == 1);
	}

	private void addRemainder(int divisor) {
		// Encontra newNum[0] = num modulo divisor, newNum[1] = num dividido
		// divisor
		BigInteger[] newNum = this.num.divideAndRemainder(BigInteger.valueOf(divisor));

		// Adiciona modulo
		this.nro.add(new Integer(newNum[1].intValue()));

		// Altera numero
		this.num = newNum[0];
	}

	private boolean ehUnicoGrupo() {
		if (this.nro.size() <= 3) {
			return false;
		}

		if (!ehGrupoZero(1) && !ehGrupoZero(2)) {
			return false;
		}

		boolean hasOne = false;

		for (int i = 3; i < this.nro.size(); i++) {

			if (this.nro.get(i).intValue() != 0) {
				if (hasOne) {
					return false;
				}

				hasOne = true;
			}
		}

		return true;
	}

	boolean ehGrupoZero(int ps) {
		if (ps <= 0 || ps >= this.nro.size()) {
			return true;
		}

		return this.nro.get(ps).intValue() == 0;
	}

	private String numToString(int numero, int escala) {
		int unidade = (numero % 10);
		int dezena = (numero % 100); // * nao pode dividir por 10 pois
		// verifica de 0..19
		int centena = (numero / 100);
		StringBuilder buf = new StringBuilder();

		if (numero != 0) {

			if (centena != 0) {

				if (dezena == 0 && centena == 1) {
					buf.append(this.Numeros[2][0] );
				} else {
					buf.append(this.Numeros[2][centena]);
				}
			}

			if ((buf.length() > 0) && (dezena != 0)) {
				buf.append(" e ");
			}

			if (dezena > 19) {
				dezena /= 10;
				buf.append(this.Numeros[1][dezena - 2]);

				if (unidade != 0) {
					buf.append(" e ");
					buf.append(this.Numeros[0][unidade]);
				}

			} else if (centena == 0 || dezena != 0) {
				buf.append(this.Numeros[0][dezena]);
			}

			buf.append(" ");

			if (numero == 1) {
				buf.append(this.Qualificadores[escala][0] );
			} else {
				buf.append(this.Qualificadores[escala][1] );
			}
		}

		return buf.toString();
	}

	public static void main(String[] args) {

		BigDecimal number = new BigDecimal("12.27");

		JExtenso teste = new JExtenso(number);

		System.out.println("Numero  : " + (new DecimalFormat().format(number)));
		System.out.println("JExtenso : " + teste.toString());
	}

}