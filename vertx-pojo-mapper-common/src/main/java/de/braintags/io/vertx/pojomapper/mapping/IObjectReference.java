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
package de.braintags.io.vertx.pojomapper.mapping;

import de.braintags.io.vertx.pojomapper.IDataStore;
import de.braintags.io.vertx.pojomapper.annotation.field.Referenced;
import de.braintags.io.vertx.pojomapper.typehandler.ITypeHandler;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * An IObjectReference can be used as carrier for those fields, which are annotated as {@link Referenced}
 * 
 * IObjectReferece is used, when objects are read from a datastore, which contain fields which are marked as
 * {@link Referenced}. Those objects are handled in two phases: in phase one, all fields, which were read from the
 * datastore, are iterated. Each field, which is marked as {@link Referenced} is stored as IObjectReference inside the
 * {@link IStoreObject#getObjectReferences()}. All other fields are treated by the suitable {@link ITypeHandler}
 * directly and stored inside the resulting instance. in phase two it is checked, wether the
 * {@link IStoreObject#getObjectReferences()} contains some entries. If so, then the referenced entities are loaded from
 * the datastore and then stored inside the suitable field. This two phase processing was choosen, cause a direct
 * reloading of referenced objects is leading into blocking threads.
 * 
 * @author Michael Remme
 * 
 */
public interface IObjectReference {

  /**
   * Get the field, where the resolved instance shall be stored
   * 
   * @return
   */
  IField getField();

  /**
   * Resolve the original object, which was referenced by its id and store it into the field of the given instance
   * 
   * @param store
   *          the store to be used
   * @param instance
   *          the instance, in which field the resolved value shall be stored inside
   * @param resultHandler
   *          the handler to be informed
   */
  void resolveObject(IDataStore store, Object instance, Handler<AsyncResult<Void>> resultHandler);

}