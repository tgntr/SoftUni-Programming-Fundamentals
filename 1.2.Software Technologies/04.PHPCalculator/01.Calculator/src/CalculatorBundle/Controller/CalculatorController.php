<?php

namespace CalculatorBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Security;
use CalculatorBundle\Entity\Calculator;
use CalculatorBundle\Form\CalculatorType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class CalculatorController extends Controller
{
    /**
     * @param Request $request
     *
     * @Route("/", name="index")
     *
     * @return \Symfony\Component\HttpFoundation\Response
     *
     */
    public function index(Request $request)
    {
        $calculator = new Calculator();
        $form = $this->createForm(CalculatorType::class, $calculator);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $leftOperand = $calculator->getLeftOperand();
            $rightOperand = $calculator->getRightOperand();
            $operator = $calculator->getOperator();
            $result = 0;
            if ($operator == "+") {
                $result = $leftOperand + $rightOperand;
            }
            else if($operator == "-") {
                $result = $leftOperand - $rightOperand;
            }
            else if($operator == "/") {
                $result = $leftOperand / $rightOperand;
            }
            else if ($operator == "*") {
                $result = $leftOperand * $rightOperand;
            }
            else if ($operator == "%") {
                $result = $leftOperand % $rightOperand;
            }
            else if ($operator == "^") {
                $result = pow($leftOperand, $rightOperand);
            }
            return $this->render('calculator/index.html.twig',
                ['result' => $result, 'calculator' => $calculator,'form' => $form->createView()]);
        }
        return $this->render('calculator/index.html.twig',
            ['form' => $form->createView()]);
    }
}
