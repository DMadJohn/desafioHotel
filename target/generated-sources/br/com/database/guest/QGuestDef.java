package br.com.database.guest;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGuestDef is a Querydsl query type for GuestDef
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGuestDef extends EntityPathBase<GuestDef> {

    private static final long serialVersionUID = 1295215947L;

    public static final QGuestDef guestDef = new QGuestDef("guestDef");

    public final StringPath documento = createString("documento");

    public final StringPath nome = createString("nome");

    public final StringPath telefone = createString("telefone");

    public QGuestDef(String variable) {
        super(GuestDef.class, forVariable(variable));
    }

    public QGuestDef(Path<? extends GuestDef> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGuestDef(PathMetadata metadata) {
        super(GuestDef.class, metadata);
    }

}

