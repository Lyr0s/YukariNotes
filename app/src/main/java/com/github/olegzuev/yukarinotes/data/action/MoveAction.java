package com.github.olegzuev.yukarinotes.data.action;

import com.github.olegzuev.yukarinotes.R;
import com.github.olegzuev.yukarinotes.common.I18N;
import com.github.olegzuev.yukarinotes.utils.Utils;
import com.github.olegzuev.yukarinotes.data.Property;

public class MoveAction extends ActionParameter {

    enum MoveType{
        unknown(0),
        targetReturn(1),
        absoluteReturn(2),
        target(3),
        absolute(4),
        targetByVelocity(5),
        absoluteByVelocity(6),
        absoluteWithoutDirection(7);

        private int value;
        MoveType(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }

        public static MoveType parse(int value){
            for(MoveType item : MoveType.values()){
                if(item.getValue() == value)
                    return item;
            }
            return unknown;
        }
    }

    private MoveType moveType;

    @Override
    protected void childInit(){
        moveType = MoveType.parse(actionDetail1);
    }

    @Override
    public String localizedDetail(int level, Property property){
        switch (moveType){
            case targetReturn:
                return I18N.getString(R.string.Change_self_position_to_s_then_return, targetParameter.buildTargetClause());
            case absoluteReturn:
                if(actionValue1.value > 0)
                    return I18N.getString(R.string.Change_self_position_s_forward_then_return, Utils.roundDownDouble(actionValue1.value));
                else
                    return I18N.getString(R.string.Change_self_position_s_backward_then_return, Utils.roundDownDouble(-actionValue1.value));
            case target:
                return I18N.getString(R.string.Change_self_position_to_s, targetParameter.buildTargetClause());
            case absolute:
            case absoluteWithoutDirection:
                if(actionValue1.value > 0)
                    return I18N.getString(R.string.Change_self_position_s_forward, Utils.roundDownDouble(actionValue1.value));
                else
                    return I18N.getString(R.string.Change_self_position_s_backward, Utils.roundDownDouble(-actionValue1.value));
            case targetByVelocity:
                if(actionValue1.value > 0)
                    return I18N.getString(R.string.Move_to_s1_in_front_of_s2_with_velocity_s3_sec, Utils.roundDownDouble(actionValue1.value), targetParameter.buildTargetClause(), actionValue2.valueString());
                else
                    return I18N.getString(R.string.Move_to_s1_behind_of_s2_with_velocity_s3_sec, Utils.roundDownDouble(-actionValue1.value), targetParameter.buildTargetClause(), actionValue2.valueString());
            case absoluteByVelocity:
                if(actionValue1.value > 0)
                    return I18N.getString(R.string.Move_forward_s1_with_velocity_s2_sec, Utils.roundDownDouble(actionValue1.value), actionValue2.valueString());
                else
                    return I18N.getString(R.string.Move_backward_s1_with_velocity_s2_sec, Utils.roundDownDouble(-actionValue1.value), actionValue2.valueString());
            default:
                return super.localizedDetail(level, property);
        }
    }

}
