
using Microsoft.EntityFrameworkCore;
using final.Models;

namespace final.data
{
    public class ProduitContext:DbContext
    {
        public ProduitContext(DbContextOptions<ProduitContext> options) : base(options) { }
        public DbSet<Produit> Final { get; set; }
    }
}
