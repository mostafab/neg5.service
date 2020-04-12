package org.neg5.data;

import java.time.Instant;

public interface Auditable {

    String getAddedBy();

    void setAddedBy(String addedBy);

    Instant getAddedAt();

    void setAddedAt(Instant addedAt);
}
