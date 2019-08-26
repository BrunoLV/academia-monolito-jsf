package br.com.valhala.academia.testes.data.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.valhala.academia.modelo.Telefone;
import br.com.valhala.academia.modelo.enums.EnumTipoTelefone;

public class TelefoneTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Telefone.class).addTemplate("cenario_telefone_valido", new Rule() {
            {
                add("ddd", "11");
                add("numero", "96378-5154");
                add("tipo", EnumTipoTelefone.CELULAR);
            }
        }).addTemplate("cenario_telefone_sem_ddd", new Rule() {
            {
                add("numero", "96378-5154");
                add("tipo", EnumTipoTelefone.CELULAR);
            }
        }).addTemplate("cenario_telefone_ddd_tamanho_diferente_dois_caracteres", new Rule() {
            {
                add("ddd", "111");
                add("numero", "96378-5154");
                add("tipo", EnumTipoTelefone.CELULAR);
            }
        }).addTemplate("cenario_telefone_sem_numero", new Rule() {
            {
                add("ddd", "11");
                add("tipo", EnumTipoTelefone.CELULAR);
            }
        }).addTemplate("cenario_telefone_sem_tipo", new Rule() {
            {
                add("ddd", "11");
                add("numero", "96378-5154");
            }
        }).addTemplate("cenario_telefone_com_nada_preenchido", new Rule() {
            {
            }
        });
    }

}
