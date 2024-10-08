package com.github.olegzuev.yukarinotes.data.action;

import com.github.olegzuev.yukarinotes.R;
import com.github.olegzuev.yukarinotes.common.I18N;
import com.github.olegzuev.yukarinotes.utils.Utils;
import com.github.olegzuev.yukarinotes.data.Property;

public class BarrierAction extends ActionParameter {

    enum BarrierType{
        unknown(0),
        physicalGuard(1),
        magicalGuard(2),
        physicalDrain(3),
        magicalDrain(4),
        bothGuard(5),
        bothDrain(6);

        private int value;
        BarrierType(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }

        public static BarrierType parse(int value){
            for(BarrierType item : BarrierType.values()){
                if(item.getValue() == value)
                    return item;
            }
            return unknown;
        }
    }

    protected BarrierType barrierType;

    @Override
    protected void childInit() {
        barrierType = BarrierType.parse(actionDetail1);
        actionValues.add(new ActionValue(actionValue1, actionValue2, null));
    }

    @Override
    public String localizedDetail(int level, Property property) {
        switch (barrierType){
            case physicalGuard:
                return I18N.getString(R.string.Cast_a_barrier_on_s1_to_nullify_s2_physical_damage_for_s3_sec,
                        targetParameter.buildTargetClause(),
                        buildExpression(level, property),
                        Utils.roundDouble(actionValue3.value));
            case magicalGuard:
                return I18N.getString(R.string.Cast_a_barrier_on_s1_to_nullify_s2_magical_damage_for_s3_sec,
                        targetParameter.buildTargetClause(),
                        buildExpression(level, property),
                        Utils.roundDouble(actionValue3.value));
            case physicalDrain:
                return I18N.getString(R.string.Cast_a_barrier_on_s1_to_absorb_s2_physical_damage_for_s3_sec,
                        targetParameter.buildTargetClause(),
                        buildExpression(level, property),
                        Utils.roundDouble(actionValue3.value));
            case magicalDrain:
                return I18N.getString(R.string.Cast_a_barrier_on_s1_to_absorb_s2_magical_damage_for_s3_sec,
                        targetParameter.buildTargetClause(),
                        buildExpression(level, property),
                        Utils.roundDouble(actionValue3.value));
            case bothDrain:
                return I18N.getString(R.string.Cast_a_barrier_on_s1_to_absorb_s2_physical_and_magical_damage_for_s3_sec,
                        targetParameter.buildTargetClause(),
                        buildExpression(level, property),
                        Utils.roundDouble(actionValue3.value));
            case bothGuard:
                return I18N.getString(R.string.Cast_a_barrier_on_s1_to_nullify_s2_physical_and_magical_damage_for_s3_sec,
                        targetParameter.buildTargetClause(),
                        buildExpression(level, property),
                        Utils.roundDouble(actionValue3.value));
            default:
                return super.localizedDetail(level, property);
        }
    }
}
