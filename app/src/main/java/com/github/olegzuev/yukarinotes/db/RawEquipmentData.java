package com.github.olegzuev.yukarinotes.db;

import com.github.olegzuev.yukarinotes.data.Property;

public class RawEquipmentData {
    public int equipment_id;
    public String equipment_name;
    public String description;
    public int promotion_level;
    public int craft_flg;
    public int equipment_enhance_point;
    public int sale_price;
    public int require_level;
    public int max_equipment_enhance_level;
    public double hp;
    public double atk;
    public double magic_str;
    public double def;
    public double magic_def;
    public double physical_critical;
    public double magic_critical;
    public double wave_hp_recovery;
    public double wave_energy_recovery;
    public double dodge;
    public double physical_penetrate;
    public double magic_penetrate;
    public double life_steal;
    public double hp_recovery_rate;
    public double energy_recovery_rate;
    public double energy_reduce_rate;
    public double accuracy;
    public String catalog;
    public int rarity;
    public int condition_equipment_id_1;
    public int consume_num_1;
    public int condition_equipment_id_2;
    public int consume_num_2;
    public int condition_equipment_id_3;
    public int consume_num_3;
    public int condition_equipment_id_4;
    public int consume_num_4;
    public int condition_equipment_id_5;
    public int consume_num_5;
    public int condition_equipment_id_6;
    public int consume_num_6;
    public int condition_equipment_id_7;
    public int consume_num_7;
    public int condition_equipment_id_8;
    public int consume_num_8;
    public int condition_equipment_id_9;
    public int consume_num_9;
    public int condition_equipment_id_10;
    public int consume_num_10;

    public Property getProperty(){
        return new Property(
                hp,
                atk,
                magic_str,
                def,
                magic_def,
                physical_critical,
                magic_critical,
                wave_hp_recovery,
                wave_energy_recovery,
                dodge,
                physical_penetrate,
                magic_penetrate,
                life_steal,
                hp_recovery_rate,
                energy_recovery_rate,
                energy_reduce_rate,
                accuracy
        );
    }
}
