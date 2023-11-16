package com.deveficiente.lojalivros.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.deveficiente.lojalivros.domain.vo.Email;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class AutorTest {

  private static String DESCRICAO_COM_401_CHARS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce non nulla eget mi porttitor mollis. In sagittis neque in consectetur tristique. Pellentesque tristique vulputate odio, vitae volutpat tellus pellentesque viverra. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vestibulum eu purus non nulla volutpat sagittis viverra sed turpis. Nam rutrum elementum nibh vitae feugiat. Suspendisse tristique orci sapien, nec efficitur leo pellentesque sed. In fringilla neque ac sem rutrum luctus. Etiam mattis arcu ligula, a dignissim tortor scelerisque in. Interdum et malesuada fames ac ante ipsum primis in faucibus. Fusce blandit molestie suscipit. Aliquam erat volutpat. Suspendisse aliquet id quam eget bibendum. Suspendisse viverra, lacus at vehicula placerat, massa tellus convallis leo, sit amet vehicula nisl est id arcu. Donec in sollicitudin dolor. Maecenas a ornare lectus, ac dapibus velit. Integer id elementum massa. Aenean et fermentum neque, nec faucibus magna. Vivamus odio ante, commodo volutpat libero id, faucibus tristique elit. Vestibulum non risus ut risus posuere dignissim vel in quam. Praesent non justo nec purus dictum finibus. Cras sollicitudin urna a sem dignissim facilisis. Donec gravida diam ac tortor venenatis malesuada. Ut id aliquam purus. Aenean venenatis finibus justo. Suspendisse pharetra auctor est, eget ultrices tortor mattis a. Vivamus dapibus quis arcu et interdum. Aliquam erat volutpat. Sed ipsum felis, mattis eu justo a, viverra elementum enim. Aliquam erat volutpat. Vivamus a malesuada erat, vitae porta ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Fusce cursus tristique rutrum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed nec vehicula risus. Donec convallis lectus eu finibus tincidunt. Fusce nisl orci, blandit sed accumsan et, egestas id mi. Ut bibendum turpis non velit blandit, sed commodo ipsum ultrices. Quisque lectus metus, maximus non lacus ut, malesuada bibendum arcu. Praesent eget velit dictum, consequat velit eu, sollicitudin sapien. Praesent convallis ac elit id consequat. Sed egestas sit amet orci ac mollis. Nulla tristique lectus sed augue consectetur accumsan. Mauris quis nisi neque. In eros urna, ullamcorper nec turpis in, dictum dapibus lectus. Proin hendrerit risus nec velit rhoncus scelerisque. Fusce luctus bibendum risus, sed ornare ex bibendum id. Praesent lacinia leo ac malesuada laoreet. Fusce dapibus tristique eleifend. Praesent id mattis magna. In rutrum id erat sed pellentesque. Morbi a eros vel arcu bibendum sagittis id eu est. Nulla lacinia tortor ut quam lobortis euismod. Nunc ut auctor risus.";

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" "})
  void deveValidarCampoNomeObrigatorio(String value) {
    assertThatThrownBy(() -> new Autor(value, null, null))
        .isExactlyInstanceOf(DomainException.class)
        .hasMessage("O campo Nome deve ser preenchido.");
  }


  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" "})
  void deveValidarCampoDescricaObrigatorio(String value) {
    assertThatThrownBy(() -> new Autor("Renato", null, value))
        .isExactlyInstanceOf(DomainException.class)
        .hasMessage("O campo Descricao deve ser preenchido.");
  }

  @Test
  void deveValidarCampoEmailObrigatorio() {
    assertThatThrownBy(() -> new Autor("Renato", null, "dummy dummy"))
        .isExactlyInstanceOf(DomainException.class)
        .hasMessage("O campo Email deve ser preenchido.");
  }

  @Test
  void deveValidarLimteCampoDescricao() {
    assertThatThrownBy(
        () -> new Autor("Renato", new Email("renato@gmail.com"), DESCRICAO_COM_401_CHARS))
        .isExactlyInstanceOf(DomainException.class)
        .hasMessage("O campo Descricao nao pode passar de 400 caracteres.");
  }

}