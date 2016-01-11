package com.focusit.jsflight.player.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class UIController implements IUIController, Serializable
{
    private static final long serialVersionUID = 1L;

    @Override
    public void load(String file) throws Exception
    {
    }

    @Override
    public void store(String file) throws Exception
    {
    }

    protected abstract String getControllerDataFilename();

    protected ObjectInputStream getInputStream(String file) throws Exception
    {
        File f = new File(file);
        if (!f.exists())
        {
            f.mkdirs();
        }

        String name = file + File.separator + getControllerDataFilename();

        f = new File(name);
        if (!f.exists())
        {
            return null;
        }

        return new ObjectInputStream(new FileInputStream(f));
    }

    protected ObjectOutputStream getOutputStream(String file) throws Exception
    {
        File f = new File(file);
        if (!f.exists())
        {
            f.mkdirs();
        }

        String name = file + File.separator + getControllerDataFilename();

        f = new File(name);
        return new ObjectOutputStream(new FileOutputStream(f));
    }

}
