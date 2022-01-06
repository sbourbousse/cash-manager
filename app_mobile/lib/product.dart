import 'package:app_mobile/import.dart';
import 'package:app_mobile/component/cardProduct.dart';
import 'package:flutter/material.dart';
import 'global.dart' as global;

void main() {
  runApp(Product());
}

class Product extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: ProductPage(),
    );
  }
}

class ProductPage extends StatefulWidget {
  ProductPage({Key ?key, this.title = "title"}) : super(key: key);

  final String title;

  @override
  _ProductPageState createState() => _ProductPageState();
}

class _ProductPageState extends State<ProductPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: FutureBuilder<List<dynamic>>(
            future: fetch(global.url + '/product'),
            builder: (BuildContext context, AsyncSnapshot snapshot) {
              if (snapshot.hasData) {
                return ListView.builder(
                    padding: EdgeInsets.all(8),
                    itemCount: snapshot.data.length,
                    itemBuilder: (BuildContext context, int index) {
                      return CardProduct(snapshot: snapshot, index: index);
                    });
              } else
                return Card(child: Text("Pas de produits retrouvées"));
            }),
      ),
    );
  }
}
