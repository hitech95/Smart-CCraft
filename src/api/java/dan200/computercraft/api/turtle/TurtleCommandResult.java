/**
 * This file is part of SmartCCraft
 *
 * Copyright (c) 2015 hitech95 <https://github.com/hitech95>
 * Copyright (c) contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package dan200.computercraft.api.turtle;

public final class TurtleCommandResult
{
    private static final TurtleCommandResult s_success = new TurtleCommandResult( true, null, null );
    private static final TurtleCommandResult s_emptyFailure = new TurtleCommandResult( false, null, null );

    public static TurtleCommandResult success()
    {
        return success( null );
    }

    public static TurtleCommandResult success( Object[] results )
    {
        if( results == null || results.length == 0 )
        {
            return s_success;
        }
        else
        {
            return new TurtleCommandResult( true, null, results );
        }
    }

    public static TurtleCommandResult failure()
    {
        return failure( null );
    }

    public static TurtleCommandResult failure( String errorMessage )
    {
        if( errorMessage == null )
        {
            return s_emptyFailure;
        }
        else
        {
            return new TurtleCommandResult( false, errorMessage, null );
        }
    }

    private final boolean m_success;
    private final String m_errorMessage;
    private final Object[] m_results;

    private TurtleCommandResult( boolean success, String errorMessage, Object[] results )
    {
        m_success = success;
        m_errorMessage = errorMessage;
        m_results = results;
    }

    public boolean isSuccess()
    {
        return m_success;
    }

    public String getErrorMessage()
    {
        return m_errorMessage;
    }

    public Object[] getResults()
    {
        return m_results;
    }
}
