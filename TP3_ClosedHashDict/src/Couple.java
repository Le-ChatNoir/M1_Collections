
import java.util.Map;

/* Implémentation de Entry paramétrable (P), susceptible d'être utilisée par plusieurs classes: table de hash ouverte, fermée, équilibrée ... */

public class Couple<K,V> implements Map.Entry<K,V> {
    private K k;
    private V v;
    
    public Couple(K k,V v) throws NullPointerException {
        /* Comme vu dans le cours, on ne tolère pas la valeur null pour la clé des tables de hachages */        
        if(k==null)
            throw new NullPointerException();
        
        this.k=k;
        this.v=v;
    }
    
    public K getKey(){
        return this.k;
    }

    public V getValue(){
        return this.v;
    }

    public V setValue(V v){
        V v2=this.v;
        this.v=v;
        return v2;
    }
    
    /* Attention: seul le critère "clé" est utilisée pour la comparaison */
    public boolean equals(Object o){
        
        /* Réference à null */
        if(o==null)
            return false;
        
        /* Même référence */
        if(o==this)
            return true;
        
        /* Permet la comparaison optimisée sur le Couple */
        if(o instanceof Couple){
            return ((Couple)o).getKey().equals(this.k);
        }
        
        /* Permet également la comparaison directement sur l'Entry */
        if(o instanceof Map.Entry){
            return ((Map.Entry)o).getKey().equals(((Map.Entry)this).getKey());
        }
        
        return false;
    }
}
