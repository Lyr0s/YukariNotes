package com.github.olegzuev.yukarinotes.data.action;

import com.github.olegzuev.yukarinotes.R;
import com.github.olegzuev.yukarinotes.common.I18N;
import com.github.olegzuev.yukarinotes.data.Property;

import java.math.RoundingMode;

public class CountBlindAction extends ActionParameter {

    enum CountType{
        unknown(-1),
        time(1),
        count(2);

        private int value;
        CountType(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }

        public static CountType parse(int value){
            for(CountType item : CountType.values()){
                if(item.getValue() == value)
                    return item;
            }
            return unknown;
        }
    }

    protected CountType countType;

    @Override
    protected void childInit() {
        countType = CountType.parse((int) actionValue1.value);
        actionValues.add(new ActionValue(actionValue2, actionValue3, null));
    }

    @Override
    public String localizedDetail(int level, Property property) {
        switch (countType){
            case time:
                return I18N.getString(R.string.In_nex_s1_sec_s2_physical_attacks_will_miss,
                        buildExpression(level, RoundingMode.UNNECESSARY, property), targetParameter.buildTargetClause());
            case count:
                return I18N.getString(R.string.In_next_s1_attacks_s2_physical_attacks_will_miss,
                        buildExpression(level, property), targetParameter.buildTargetClause());
            default:
                return super.localizedDetail(level, property);
        }
    }
}
