package com.albertsilva.dev.dscatalog.projection;

import com.albertsilva.dev.dscatalog.domain.Identifiable;

public interface ProductProjection extends Identifiable<Long> {

  String getName();
}
