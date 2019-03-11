# elasticsearch-store
simple store, one entity, for testing search engine

# initialization
run `init` in shell for creating test data
will be created two types of indices: product-store, product-store-analyze.
---
Index `product-store-analyze` has a defined analyzer for `title` and `description` fields.


# Install elasticsearch
https://www.elastic.co/guide/en/elasticsearch/reference/current/getting-started-install.html

#cUrl delete all data
curl -XDELETE 'http://localhost:9200/*'