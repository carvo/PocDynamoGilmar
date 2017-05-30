package com.poc.com.poc.dynamo.entity;

import java.util.Calendar;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * The Class SituacaoIdentificadorEntity.
 */
@DynamoDBTable(tableName = "SituacaoIdentificador")
public class SituacaoIdentificadorEntity {

    /** The id. */
    @DynamoDBHashKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey
    private String id;

    /** The concessionariaId. */
    @DynamoDBIndexHashKey(attributeName = "concessionariaId", globalSecondaryIndexName = "_ConcessionariaIndex")
    private Integer concessionariaId;

    /** The nuIdentificador. */
    @DynamoDBRangeKey
    private Long nuIdentificador;

    /** The placa. */
    @DynamoDBAttribute(attributeName = "placa")
    private String placa;

    /** The grupos. */
    @DynamoDBAttribute(attributeName = "grupo")
    private Integer grupo;

    /** The situacao. */
    @DynamoDBAttribute(attributeName = "situacao")
    private Integer situacao;

    /** The motivo bloqueio. */
    @DynamoDBAttribute(attributeName = "motivoBloqueio")
    private Integer motivoBloqueio;

    /** The tem passagem. */
    @DynamoDBAttribute(attributeName = "temPassagem")
    private Boolean temPassagem;

    /** The plano. */
    @DynamoDBAttribute(attributeName = "tipoProduto")
    private Integer tipoProduto;

    /** The saldo cliente. */
    @DynamoDBAttribute(attributeName = "dataProcessada")
    private Long dataProcessada;

    /** The id cliente. */
    @DynamoDBAttribute(attributeName = "idCliente")
    private Integer idCliente;

    /** The id conta. */
    @DynamoDBAttribute(attributeName = "idConta")
    private Integer idConta;

    /** The json. */
    @DynamoDBAttribute(attributeName = "json")
    private String json;

    /**
     * Instantiates a new situacao identificador entity.
     */
    public SituacaoIdentificadorEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getConcessionariaId() {
        return concessionariaId;
    }

    public void setConcessionariaId(Integer concessionariaId) {
        this.concessionariaId = concessionariaId;
    }

    public Long getNuIdentificador() {
        return nuIdentificador;
    }

    public void setNuIdentificador(Long nuIdentificador) {
        this.nuIdentificador = nuIdentificador;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Integer getMotivoBloqueio() {
        return motivoBloqueio;
    }

    public void setMotivoBloqueio(Integer motivoBloqueio) {
        this.motivoBloqueio = motivoBloqueio;
    }

    public Boolean getTemPassagem() {
        return temPassagem;
    }

    public void setTemPassagem(Boolean temPassagem) {
        this.temPassagem = temPassagem;
    }

    public Integer getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(Integer tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Long getDataProcessada() {
        return dataProcessada;
    }

    public void setDataProcessada(Long dataProcessada) {
        this.dataProcessada = dataProcessada;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((concessionariaId == null) ? 0 : concessionariaId.hashCode());
        result = prime * result + ((dataProcessada == null) ? 0 : dataProcessada.hashCode());
        result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
        result = prime * result + ((idConta == null) ? 0 : idConta.hashCode());
        result = prime * result + ((json == null) ? 0 : json.hashCode());
        result = prime * result + ((motivoBloqueio == null) ? 0 : motivoBloqueio.hashCode());
        result = prime * result + ((nuIdentificador == null) ? 0 : nuIdentificador.hashCode());
        result = prime * result + ((placa == null) ? 0 : placa.hashCode());
        result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
        result = prime * result + ((temPassagem == null) ? 0 : temPassagem.hashCode());
        result = prime * result + ((tipoProduto == null) ? 0 : tipoProduto.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SituacaoIdentificadorEntity other = (SituacaoIdentificadorEntity) obj;
        if (concessionariaId == null) {
            if (other.concessionariaId != null)
                return false;
        } else if (!concessionariaId.equals(other.concessionariaId))
            return false;
        if (dataProcessada == null) {
            if (other.dataProcessada != null)
                return false;
        } else if (!dataProcessada.equals(other.dataProcessada))
            return false;
        if (grupo == null) {
            if (other.grupo != null)
                return false;
        } else if (!grupo.equals(other.grupo))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (idCliente == null) {
            if (other.idCliente != null)
                return false;
        } else if (!idCliente.equals(other.idCliente))
            return false;
        if (idConta == null) {
            if (other.idConta != null)
                return false;
        } else if (!idConta.equals(other.idConta))
            return false;
        if (json == null) {
            if (other.json != null)
                return false;
        } else if (!json.equals(other.json))
            return false;
        if (motivoBloqueio == null) {
            if (other.motivoBloqueio != null)
                return false;
        } else if (!motivoBloqueio.equals(other.motivoBloqueio))
            return false;
        if (nuIdentificador == null) {
            if (other.nuIdentificador != null)
                return false;
        } else if (!nuIdentificador.equals(other.nuIdentificador))
            return false;
        if (placa == null) {
            if (other.placa != null)
                return false;
        } else if (!placa.equals(other.placa))
            return false;
        if (situacao == null) {
            if (other.situacao != null)
                return false;
        } else if (!situacao.equals(other.situacao))
            return false;
        if (temPassagem == null) {
            if (other.temPassagem != null)
                return false;
        } else if (!temPassagem.equals(other.temPassagem))
            return false;
        if (tipoProduto == null) {
            if (other.tipoProduto != null)
                return false;
        } else if (!tipoProduto.equals(other.tipoProduto))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(dataProcessada);
        
        StringBuilder builder = new StringBuilder();
        builder.append("SituacaoIdentificadorEntity [id=");
        builder.append(id);
        builder.append(", concessionariaId=");
        builder.append(concessionariaId);
        builder.append(", nuIdentificador=");
        builder.append(nuIdentificador);
        builder.append(", placa=");
        builder.append(placa);
        builder.append(", grupo=");
        builder.append(grupo);
        builder.append(", situacao=");
        builder.append(situacao);
        builder.append(", motivoBloqueio=");
        builder.append(motivoBloqueio);
        builder.append(", temPassagem=");
        builder.append(temPassagem);
        builder.append(", tipoProduto=");
        builder.append(tipoProduto);
        builder.append(", dataProcessada=");
        builder.append(dataProcessada);
        builder.append(", dataProcessadaPorExtenso=");
        builder.append(c.getTime());
        builder.append(", saldoCliente=");
        builder.append(", idCliente=");
        builder.append(idCliente);
        builder.append(", idConta=");
        builder.append(idConta);
        builder.append(", limiteOperacional=");
        builder.append(", utilizarLimite=");
        builder.append(", json=");
        builder.append(json);
        builder.append("]");
        return builder.toString();
    }

}
