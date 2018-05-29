package br.com.database.stay;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStayDef is a Querydsl query type for StayDef
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStayDef extends EntityPathBase<StayDef> {

    private static final long serialVersionUID = 1784173823L;

    public static final QStayDef stayDef = new QStayDef("stayDef");

    public final BooleanPath adicionalVeiculo = createBoolean("adicionalVeiculo");

    public final StringPath dataEntrada = createString("dataEntrada");

    public final StringPath dataSaida = createString("dataSaida");

    public final StringPath documento = createString("documento");

    public QStayDef(String variable) {
        super(StayDef.class, forVariable(variable));
    }

    public QStayDef(Path<? extends StayDef> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStayDef(PathMetadata metadata) {
        super(StayDef.class, metadata);
    }

}

