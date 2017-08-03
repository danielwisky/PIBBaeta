package br.com.danielwisky.pibbaeta.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class MyDaoGenerator {

  public static void main(String[] args) throws Exception {
    Schema schema = new Schema(1, "br.com.danielwisky.pibbaeta.dao");
    schema.enableKeepSectionsByDefault();

    addTables(schema);

    new DaoGenerator().generateAll(schema,"./app/src/main/java");
  }

  private static void addTables(final Schema schema) {
    addPedidoOracaoEntities(schema);
    Entity tipoProgramacao = addTipoProgramacaoEntities(schema);
    addProgramacaoEntities(schema, tipoProgramacao);
  }

  // This is use to describe the colums of your table
  private static Entity addPedidoOracaoEntities(final Schema schema) {
    Entity pedido = schema.addEntity("PedidoOracao");
    pedido.addIdProperty().primaryKey().autoincrement();
    pedido.addStringProperty("nome").notNull();
    pedido.addStringProperty("email");
    pedido.addStringProperty("telefone");
    pedido.addStringProperty("pedido").notNull();
    return pedido;
  }

  private static Entity addTipoProgramacaoEntities(final Schema schema) {
    Entity tipoProgramacao = schema.addEntity("TipoProgramacao");
    tipoProgramacao.addIdProperty().primaryKey();
    tipoProgramacao.addStringProperty("descricao").notNull();
    return tipoProgramacao;
  }

  private static Entity addProgramacaoEntities(final Schema schema, final Entity tipoProgramacao) {
    Entity programacao = schema.addEntity("Programacao");
    programacao.addIdProperty().primaryKey();
    programacao.addStringProperty("titulo").notNull();
    programacao.addStringProperty("descricao").notNull();
    programacao.addDateProperty("dataInicio").notNull();
    programacao.addDateProperty("dataTermino").notNull();
    programacao.addStringProperty("local").notNull();
    programacao.addStringProperty("endereco");
    programacao.addStringProperty("urlBanner");

    // add the foreign key "tipoProgramacaoId" to the "programacao" entity
    Property tipoProgramacaoIdProperty = programacao.addLongProperty("tipoProgramacaoId").getProperty();
    // set up a to-one relation to the "programacao" entity
    programacao.addToOne(tipoProgramacao, tipoProgramacaoIdProperty);

    return programacao;
  }
}