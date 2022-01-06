import 'package:app_mobile/component/cardCart.dart';
import 'package:app_mobile/component/cardProduct.dart';
import 'package:flutter/material.dart';

import 'global.dart' as global;
import 'import.dart';
import 'main.dart';

void main() {
  runApp(Cart());
}

class Cart extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: CartPage(),
    );
  }
}

class CartPage extends StatefulWidget {
  CartPage({Key ?key, this.title = "title"}) : super(key: key);

  final String title;

  @override
  _CartPageState createState() => _CartPageState();
}

class _CartPageState extends State<CartPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
      child: FutureBuilder<List<dynamic>>(
          future: fetch(global.url + '/cart/${cartId}'),
          builder: (BuildContext context, AsyncSnapshot snapshot) {
            if (snapshot.hasData) {
              return Column(
                children: [
                  Expanded(
                      child: ListView.builder(
                          padding: EdgeInsets.all(8),
                          itemCount: snapshot.data.length,
                          itemBuilder: (BuildContext context, int index) {
                            //print(snapshot.data);
                            return CardCart(snapshot: snapshot, index: index);
                          })),
                  Container(
                    padding: EdgeInsets.all(10),
                    margin: EdgeInsets.all(10),
                    color: Colors.blue,
                    width: double.infinity,
                    child:
                        Text("Total :  " + totalPriceCart(snapshot).toString()),
                  )
                ],
              );
            } else
              return Card(child: Text("Votre panier est vide"));
          }),
    ));
  }
}
