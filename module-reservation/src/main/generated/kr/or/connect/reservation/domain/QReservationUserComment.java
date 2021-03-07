package kr.or.connect.reservation.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationUserComment is a Querydsl query type for ReservationUserComment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReservationUserComment extends EntityPathBase<ReservationUserComment> {

    private static final long serialVersionUID = 1348141416L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationUserComment reservationUserComment = new QReservationUserComment("reservationUserComment");

    public final kr.or.connect.reservation.domain.audite.QBaseEntity _super = new kr.or.connect.reservation.domain.audite.QBaseEntity(this);

    public final StringPath comment = createString("comment");

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final QProduct product;

    public final QReservationInfo reservationInfo;

    public final NumberPath<Double> score = createNumber("score", Double.class);

    public QReservationUserComment(String variable) {
        this(ReservationUserComment.class, forVariable(variable), INITS);
    }

    public QReservationUserComment(Path<? extends ReservationUserComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationUserComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationUserComment(PathMetadata metadata, PathInits inits) {
        this(ReservationUserComment.class, metadata, inits);
    }

    public QReservationUserComment(Class<? extends ReservationUserComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.reservationInfo = inits.isInitialized("reservationInfo") ? new QReservationInfo(forProperty("reservationInfo"), inits.get("reservationInfo")) : null;
    }

}

