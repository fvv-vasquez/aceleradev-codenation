package br.com.codenation.desafioexe;

import java.util.List;
import java.util.ArrayList;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		Integer total = 14;
		final List<Integer> fibList = new ArrayList<>();
        fibList.add(0);
        fibList.add(1);

        for (int cont = 2; fibList.size() <= total; cont++){
            fibList.add((fibList.get(cont - 1)) + (fibList.get(cont - 2)));
        }
        return fibList;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}