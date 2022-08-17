import PropTypes from 'prop-types';
import { createContext, useCallback, useEffect, useState, useRef } from 'react';

const LeilaoProcessContext = createContext({});

export function ProviderLeilaoProcess({ children }) {
    const [userConnected, setUserConneted] = useState([])
    const [itensLeilao, setItemLeilao] = useState([])
    const [leilaoAtivo, setLeilao] = useState([])
    const [eventos, setEventos] = useState([])
    const socketIORef = useRef()

    const handleSocketMessage = useCallback(message => {
        switch (message.msgType) {
            case 'LIST-ITEMS-AVAILABLES':
                setItemLeilao(message.data)
                break

            case 'LIST-USER-AVAILABLES':
                setUserConneted(message.data)
                break

            case 'LEILAO-CREATED':
                setLeilao([JSON.parse(message.data)])
                break

            case 'LEILAO-DATA':
                setLeilao(JSON.parse(message.data))
                break

            case 'OCORRENCIAS':
                console.log('OCORRENCIAS', message.data)
                setEventos(message.data)
                break

            default:
                console.log('message not handling yet', message)
        }
    }, [])

    const join = useCallback((data) => {
        socketIORef.current.send(JSON.stringify({ type: 'join', data: { ...data, valorLance: 1 } }))
    }, [])

    useEffect(() => {
        if (!socketIORef.current?.connected) {
            const wss = process.env.NODE_ENV === 'development' ? 'ws://localhost:8081/leilao' : 'wss://leilao-app.herokuapp.com/leilao'
            socketIORef.current = new WebSocket(wss);
            socketIORef.current.connected = true

            socketIORef.current.onerror = () => {
                console.log('erro no socket')
                socketIORef.current.connected = false
                setUserConneted([])
            }

            socketIORef.current.onclose = () => {
                console.log('disconneted')
                socketIORef.current.connected = false
                setUserConneted([])
            }

            socketIORef.current.onopen = () => { }

            socketIORef.current.onmessage = (data) => {
                handleSocketMessage(JSON.parse(data.data))
            }
        }


    }, [userConnected, handleSocketMessage])

    return (
        <LeilaoProcessContext.Provider value={{ userConnected, itensLeilao, eventos, leilaoAtivo, join }}>
            {children}
        </LeilaoProcessContext.Provider>
    )
}

ProviderLeilaoProcess.propTypes = {
    children: PropTypes.any.isRequired
}

export default LeilaoProcessContext