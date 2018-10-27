package org.neg5.data;

import java.io.Serializable;

public interface IdDataObject<IdType extends Serializable> {

    IdType getId();

    void setId(IdType id);
}
