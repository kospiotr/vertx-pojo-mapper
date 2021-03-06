/*
 * #%L
 * vertx-pojo-mapper-common-test
 * %%
 * Copyright (C) 2017 Braintags GmbH
 * %%
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * #L%
 */
package de.braintags.vertx.jomnigate.dataaccess.datatypetests;

import java.util.List;

import org.junit.Test;

import de.braintags.vertx.jomnigate.dataaccess.query.IQuery;
import de.braintags.vertx.jomnigate.testdatastore.ResultContainer;
import de.braintags.vertx.jomnigate.testdatastore.mapper.typehandler.BaseRecord;
import de.braintags.vertx.jomnigate.testdatastore.mapper.typehandler.EmbeddedMapper_Map;
import de.braintags.vertx.jomnigate.testdatastore.mapper.typehandler.SimpleMapperEmbedded;
import io.vertx.ext.unit.TestContext;

/**
 * Tests for testing embedded Maps
 * 
 * @author Michael Remme
 * 
 */
public class EmbeddedMapTest extends AbstractDatatypeTest {

  public EmbeddedMapTest() {
    super("simpleMapper");
  }

  @Test
  public void extreme(TestContext context) {
    clearTable(context, EmbeddedMapper_Map.class.getSimpleName());
    EmbeddedMapper_Map record = new EmbeddedMapper_Map();
    record.simpleMapper = null;
    saveRecord(context, record);
    IQuery<EmbeddedMapper_Map> query = getDataStore(context).createQuery(EmbeddedMapper_Map.class);
    List list = findAll(context, query);
    context.assertEquals(1, list.size());
    EmbeddedMapper_Map loaded = (EmbeddedMapper_Map) list.get(0);
    context.assertNull(loaded.simpleMapper);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.braintags.vertx.jomnigate.testdatastore.typehandler.AbstractTypeHandlerTest#createInstance()
   */
  @Override
  public BaseRecord createInstance(TestContext context) {
    BaseRecord mapper = new EmbeddedMapper_Map();
    return mapper;
  }

  @Override
  protected String getTestFieldName() {
    return "simpleMapper";
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.braintags.vertx.jomnigate.dataaccess.datatypetests.AbstractDatatypeTest#validateAfterSave(io.vertx.ext.unit.
   * TestContext, java.lang.Object, de.braintags.vertx.jomnigate.testdatastore.ResultContainer)
   */
  @Override
  protected void validateAfterSave(TestContext context, Object record, ResultContainer resultContainer) {
    super.validateAfterSave(context, record, resultContainer);
    EmbeddedMapper_Map loaded = (EmbeddedMapper_Map) record;
    context.assertNotNull(loaded.simpleMapper.get(0).id);
    loaded.simpleMapper.put(10, new SimpleMapperEmbedded("updated", "updated value"));
    saveRecord(context, loaded);
  }

}
