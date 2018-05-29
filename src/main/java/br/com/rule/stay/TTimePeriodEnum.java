package br.com.rule.stay;

public enum TTimePeriodEnum {
    FUTURE(0),
    PRESENT(1),
    PAST(2);

    private int period;

    private TTimePeriodEnum(int period){
        this.period = period;
    }

    public int getCode(){
        return period;
    }
}
