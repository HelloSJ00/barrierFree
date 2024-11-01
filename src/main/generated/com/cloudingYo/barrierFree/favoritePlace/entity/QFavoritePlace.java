package com.cloudingYo.barrierFree.favoritePlace.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFavoritePlace is a Querydsl query type for FavoritePlace
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavoritePlace extends EntityPathBase<FavoritePlace> {

    private static final long serialVersionUID = 1483261900L;

    public static final QFavoritePlace favoritePlace = new QFavoritePlace("favoritePlace");

    public final com.cloudingYo.barrierFree.common.entity.QBaseEntity _super = new com.cloudingYo.barrierFree.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final NumberPath<Long> placeId = createNumber("placeId", Long.class);

    public final NumberPath<Integer> placeKey = createNumber("placeKey", Integer.class);

    public final StringPath placename = createString("placename");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QFavoritePlace(String variable) {
        super(FavoritePlace.class, forVariable(variable));
    }

    public QFavoritePlace(Path<? extends FavoritePlace> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFavoritePlace(PathMetadata metadata) {
        super(FavoritePlace.class, metadata);
    }

}

