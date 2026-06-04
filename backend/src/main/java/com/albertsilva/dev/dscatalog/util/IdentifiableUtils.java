package com.albertsilva.dev.dscatalog.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.albertsilva.dev.dscatalog.projection.Identifiable;

public final class IdentifiableUtils {

  public static <ID, T extends Identifiable<ID>> List<T> reorderByReference(List<T> unordered,
      List<? extends Identifiable<ID>> ordered) {

    Map<ID, T> map = new HashMap<>();

    for (T entity : unordered) {
      map.put(entity.getId(), entity);
    }

    List<T> result = new ArrayList<>();

    for (Identifiable<ID> identifiable : ordered) {
      T matched = map.get(identifiable.getId());

      if (matched != null) {
        result.add(matched);
      }
    }

    return result;
  }

}
