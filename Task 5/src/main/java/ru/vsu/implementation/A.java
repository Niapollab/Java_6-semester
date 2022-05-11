package ru.vsu.implementation;

import ru.vsu.interfaces.SomeInterface;

public class A implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.print("A");
    }
}