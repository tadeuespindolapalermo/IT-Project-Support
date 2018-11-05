package com.github.tadeuespindolapalermo.itprojectsupport.utilities;

import java.util.Collection;

public class Validators {

	public static <T> boolean collectionValid(Collection<T> collection) {
		return collection != null && !collection.isEmpty();
	}

}
