package org.nobledev.midgard.data

import org.bson.Document

infix fun String.eq(value: Any) = Document(this, value)