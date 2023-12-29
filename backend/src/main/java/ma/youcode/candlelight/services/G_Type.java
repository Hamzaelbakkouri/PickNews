package ma.youcode.candlelight.services;

public interface G_Type<Req , Res, Identifier> {
    public Res create(Req newElement);
    public Res update(Req elementBody);
    public Res findById(Identifier identifier);
    public String delete(Identifier identifier);
}
