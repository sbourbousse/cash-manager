import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../import.dart';

class CardProduct extends StatefulWidget {
  const CardProduct({Key ?key, required this.snapshot, this.index = 0}) : super(key: key);

  final AsyncSnapshot<dynamic> snapshot;
  final int index;

  @override
  _CardProductState createState() => _CardProductState();
}

class _CardProductState extends State<CardProduct> {
  TextEditingController qteController = new TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Card(
        elevation: 1.0,
        child: Column(
          children: [
            ListTile(
              title: Text(widget.snapshot.data[widget.index]["name"]),
              subtitle: Text(
                  widget.snapshot.data[widget.index]["price"].toString() +
                      " €"),
            ),
            Padding(
              padding: EdgeInsets.symmetric(horizontal: 5.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Container(
                    width: 50.0,
                    height: 35,
                    decoration: BoxDecoration(
                        color: Theme.of(context).scaffoldBackgroundColor,
                        borderRadius: BorderRadius.circular(10.0)),
                    margin: EdgeInsets.all(10.0),
                    padding: EdgeInsets.symmetric(horizontal: 10.0),
                    child: TextField(
                      controller: qteController,
                      keyboardType: TextInputType.numberWithOptions(
                          decimal: true, signed: false),
                      inputFormatters: <TextInputFormatter>[
                        FilteringTextInputFormatter.digitsOnly
                      ],
                      decoration: InputDecoration(
                          border: InputBorder.none, hintText: "Qte"),
                    ),
                  ),
                  ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      textStyle: TextStyle(fontSize: 16),
                      minimumSize: Size(50, 25),
                    ),
                    onPressed: () {
                      addProduct(widget.snapshot.data[widget.index],
                          int.parse(qteController.text));
                    },
                    child: Text(
                      'Add',
                    ),
                  ),
                ],
              ),
            ),
          ],
        ));
  }
}
