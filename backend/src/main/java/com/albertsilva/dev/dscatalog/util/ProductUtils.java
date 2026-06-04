package com.albertsilva.dev.dscatalog.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.albertsilva.dev.dscatalog.entity.Product;
import com.albertsilva.dev.dscatalog.projection.ProductProjection;

public class ProductUtils {

  public static List<Product> replace(List<Product> unordered, List<ProductProjection> ordered) {
    Map<Long, Product> map = new HashMap<>();
    for (Product product : unordered) {
      map.put(product.getId(), product);
    }

    List<Product> result = new ArrayList<>();
    for (ProductProjection projection : ordered) {
      Product product = map.get(projection.getId());
      if (product != null) {
        result.add(product);
      }
    }

    return result;
  }

}
