package com.neox.main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.area.Area;
import com.neox.inventory.model.user.UserView;
import com.neox.inventory.service.AreaService;
import com.neox.inventory.service.MaterialService;
import com.neox.inventory.service.MovementService;
import com.neox.inventory.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		
		//System.out.println(AreaService.getAreaMaterialList(new Integer(1)));

	}

}

/*
 -1719912462
[1 admin:admin admin] => 1527086220
[8 GUS.MERINO:GUSTAVO MERINO VAZQUEZ] => 505231702
[10 JOR.TLATELPA:JORGE ANGEL TLATELPA ROMERO] => 1328323494
[11 JOS.MODESTO:JOSE LUIS MODESTO RODRIGUEZ] => 1311292881
[13 LEO.PEREZ:LEONARDO PEREZ BARRERA] => 1408533352
0
*/
