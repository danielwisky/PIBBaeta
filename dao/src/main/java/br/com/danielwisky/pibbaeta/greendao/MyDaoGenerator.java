package br.com.danielwisky.pibbaeta.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Index;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class MyDaoGenerator {

  public static void main(String[] args) throws Exception {
    Schema schema = new Schema(2, "br.com.danielwisky.pibbaeta.dao");
    schema.enableKeepSectionsByDefault();
    addTables(schema);
    new DaoGenerator().generateAll(schema,"./app/src/main/java");
  }

  private static void addTables(final Schema schema) {
    addPedidoOracaoEntities(schema);
    addProgramacaoEntities(schema);
    addFeedbackEntities(schema);
  }

  // This is use to describe the colums of your table
  private static Entity addPedidoOracaoEntities(final Schema schema) {
    final Entity pedido = schema.addEntity("PedidoOracao");
    pedido.addIdProperty().primaryKey().autoincrement();
    pedido.addStringProperty("nome").notNull();
    pedido.addStringProperty("email");
    pedido.addStringProperty("telefone");
    pedido.addStringProperty("pedido").notNull();
    pedido.addBooleanProperty("meuPedido");
    pedido.addIndex(getIdExternoProperty(pedido));
    pedido.implementsInterface("android.os.Parcelable");
    return pedido;
  }

  private static Entity addFeedbackEntities(final Schema schema) {
    final Entity feedback = schema.addEntity("Feedback");
    feedback.addIdProperty().primaryKey().autoincrement();
    feedback.addStringProperty("descricao").notNull();
    feedback.implementsInterface("android.os.Parcelable");
    return feedback;
  }

  private static Entity addProgramacaoEntities(final Schema schema) {
    final Entity programacao = schema.addEntity("Programacao");
    programacao.addIdProperty().primaryKey();
    programacao.addStringProperty("titulo").notNull();
    programacao.addStringProperty("descricao").notNull();
    programacao.addStringProperty("tipo").notNull();
    programacao.addDateProperty("dataInicio").notNull();
    programacao.addDateProperty("dataTermino").notNull();
    programacao.addStringProperty("local").notNull();
    programacao.addStringProperty("endereco");
    programacao.addStringProperty("urlBanner");
    programacao.addStringProperty("observacao");
    programacao.addIndex(getIdExternoProperty(programacao));
    programacao.implementsInterface("android.os.Parcelable");
    return programacao;
  }

  private static Index getIdExternoProperty(Entity entity) {
    final Property idExterno = entity.addStringProperty("idExterno").getProperty();
    final Index idxIdExterno = new Index();
    idxIdExterno.addProperty(idExterno);
    idxIdExterno.makeUnique();
    return idxIdExterno;
  }
}