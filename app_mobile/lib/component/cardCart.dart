import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../import.dart';

class CardCart extends StatefulWidget {
  const CardCart({Key ?key, required this.snapshot, this.index = 0}) : super(key: key);

  final AsyncSnapshot<dynamic> snapshot;
  final int index;

  @override
  _CardCartState createState() => _CardCartState();
}

class _CardCartState extends State<CardCart> {
  TextEditingController qteController = new TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Card(
        elevation: 1.0,
        child: Column(
          children: [
            ListTile(
              title: Text(widget.snapshot.data[widget.index]
                  ["productRepresentation"]["name"]),
              subtitle: Text("Prix total :  " +
                  totalPrice(widget.snapshot.data[widget.index]).toString()),
              trailing: IconButton(
                icon: const Icon(Icons.delete),
                color: Color(0xFF8e9094),
                iconSize: 24.0,
                onPressed: () {},
              ),
            ),
            ListTile(
              title: Text("Quantite :  " +
                  widget.snapshot.data[widget.index]["amount"].toString()),
            ),
          ],
        ));
  }
}
