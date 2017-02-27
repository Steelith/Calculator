package kisielarobert.calculator;

    /*public*/ enum Operation {

    NONE(""), ADD("+"), SUBTRACT("-");

    private final String key;

    /*private*/ Operation (String key){

        this.key = key;
    }

    public static Operation operationFromKey (String key){
        for (Operation operation : values()) {
            if(operation.key.equals(key)){
                return operation;
            }
        }
        return NONE;
    }
}
