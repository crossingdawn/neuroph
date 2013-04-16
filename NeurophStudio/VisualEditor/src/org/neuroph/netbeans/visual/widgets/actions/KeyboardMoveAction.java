/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.netbeans.visual.widgets.actions;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.Widget;
import org.neuroph.core.Connection;
import org.neuroph.core.Layer;
import org.neuroph.core.Neuron;
import org.neuroph.netbeans.visual.widgets.NeuralNetworkScene;
/**
 *
 * @author Ana
 */
public class KeyboardMoveAction extends WidgetAction.Adapter {

    NeuralNetworkScene scene;
    // Layer layer;
    private MoveProvider provider;

    public KeyboardMoveAction(NeuralNetworkScene scene) {
        this.scene = scene;
        //this.layer = layer;
        this.provider = ActionFactory.createDefaultMoveProvider();
        //keyPressed(widget, new WidgetKeyEvent(0, null));
    }

    @Override
    public WidgetAction.State keyPressed(Widget widget, WidgetAction.WidgetKeyEvent event) {
        for (Object object : scene.getSelectedObjects()) {
            if ((event.getKeyCode() == KeyEvent.VK_DELETE)) {
                if (object instanceof Layer) {
                    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete selected layer?", "Delete Layer?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Layer myLayer = (Layer) object;
                        myLayer.getParentNetwork().removeLayer(myLayer);
                        scene.refresh();
                        //provider.movementStarted(widget);
                    }
                }
                if (object instanceof Neuron) {
                    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete selected neuron?", "Delete Neuron", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Neuron myNeuron = (Neuron) object;
                        myNeuron.getParentLayer().removeNeuron(myNeuron);
                        scene.refresh();
                        //provider.movementStarted(widget);
                    }
                }
                if (object instanceof Connection) {
                    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete selected connection?", "Delete Connection", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Neuron srcNeuron = ((Connection) object).getFromNeuron();
                        Neuron trgNeuron = ((Connection) object).getToNeuron();
                        trgNeuron.removeInputConnectionFrom(srcNeuron);
                        scene.refresh();
                        //provider.movementStarted(widget);
                    }
                }
            }
        }



        return State.CONSUMED;
    }
}
