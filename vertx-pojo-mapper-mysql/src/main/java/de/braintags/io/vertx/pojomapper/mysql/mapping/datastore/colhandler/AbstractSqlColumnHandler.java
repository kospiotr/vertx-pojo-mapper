/*
 * #%L
 * vertx-pojongo
 * %%
 * Copyright (C) 2015 Braintags GmbH
 * %%
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * #L%
 */

package de.braintags.io.vertx.pojomapper.mysql.mapping.datastore.colhandler;

import de.braintags.io.vertx.pojomapper.annotation.field.Property;
import de.braintags.io.vertx.pojomapper.mapping.IField;
import de.braintags.io.vertx.pojomapper.mapping.datastore.impl.AbstractColumnHandler;

/**
 * An abstract implementation for use with SQL based datastores The implementation checks, wether
 * 
 * @author Michael Remme
 * 
 */

public abstract class AbstractSqlColumnHandler extends AbstractColumnHandler {

  /**
   * @param classesToDeal
   */
  public AbstractSqlColumnHandler(Class<?>... classesToDeal) {
    super(classesToDeal);
  }

  @Override
  public final Object generate(IField field) {
    if (field.getMapper().getIdField() == field) {
      return generateIdColumn(field);
    } else {
      return generateColumn(field);
    }
  }

  /**
   * Generates a sequence like "id int(10) NOT NULL auto_increment"
   * 
   * @param field
   * @return
   */
  protected String generateIdColumn(IField field) {
    String propName = field.getColumnInfo().getName();
    Property prop = (Property) field.getAnnotation(Property.class);
    int scale = prop.scale();
    scale = scale == 0 ? 10 : scale;
    return String.format("%s(%d) NOT NULL auto_increment", propName, scale);
  }

  /**
   * Generate the sequence to build a column inside the datastore
   * 
   * @param field
   * @return
   */
  protected abstract String generateColumn(IField field);
}
