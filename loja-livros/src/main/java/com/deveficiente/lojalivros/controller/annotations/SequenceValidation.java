package com.deveficiente.lojalivros.controller.annotations;

import com.deveficiente.lojalivros.controller.annotations.SequenceValidation.First;
import com.deveficiente.lojalivros.controller.annotations.SequenceValidation.Second;
import javax.validation.GroupSequence;

@GroupSequence({First.class, Second.class})
public interface SequenceValidation {

  interface First {

  }

  interface Second {

  }

}
