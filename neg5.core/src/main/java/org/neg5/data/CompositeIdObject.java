package org.neg5.data;

import java.io.Serializable;

public interface CompositeIdObject<IdType extends Serializable & CompositeId> extends IdDataObject<IdType> {

}
