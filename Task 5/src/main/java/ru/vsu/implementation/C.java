package ru.vsu.implementation;

import ru.vsu.interfaces.SomeOtherInterface;

public class C implements SomeOtherInterface {
    @Override
    public void doSomething() {
        System.out.print("C");
    }
}