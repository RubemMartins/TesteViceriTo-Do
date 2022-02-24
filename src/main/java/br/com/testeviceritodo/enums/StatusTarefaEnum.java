package br.com.testeviceritodo.enums;

public enum StatusTarefaEnum {
	 CONCLUIDA("CONCLUIDA"),
	    PENDENTE("PENDENTE");

	    private String value;

	    StatusTarefaEnum(String value) {
	        this.value = value;
	    }

	    @Override
	    public String toString() {
	        return value;
	    }
}
