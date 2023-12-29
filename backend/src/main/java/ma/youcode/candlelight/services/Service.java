package ma.youcode.candlelight.services;

public interface Service<Dto, Identifier> {
    
    public Dto create(Dto newElement);
    public Dto update(Identifier identifier, Dto elementBody);
    public Dto findById(Identifier identifier);
    public Dto delete(Identifier identifier);

}
