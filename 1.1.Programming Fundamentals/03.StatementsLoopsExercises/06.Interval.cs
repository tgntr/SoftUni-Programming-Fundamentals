﻿using System;

class Interval
{
    static void Main()
    {
        int a = int.Parse(Console.ReadLine());
        int b = int.Parse(Console.ReadLine());
        for (int i = Math.Min(a,b); i <= Math.Max(a,b); i++)
        {
            Console.WriteLine(i);
        }
    }
}

