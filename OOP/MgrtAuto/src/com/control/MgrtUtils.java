package com.control;

import java.util.*;
import com.infa.*;

public final class MgrtUtils {

	public static void printStringList(List<String> strList, String prefix) {

		for (String str : strList) {
			System.out.println(str);
		}
	}

	public static void printInfaObjList(List<InfaObjFactory> infaObjList, String prefix) {

		for (InfaObjFactory infaObj : infaObjList) {
			System.out.println( prefix + infaObj.toString());
		}
	}
	
	public static void printStringSet(Set<String> strSet, String prefix) {
		for (String str: strSet) {
			System.out.println(str);
		}
	}
}
